package com.sms.services;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sms.constants.StaffState;
import com.sms.dtos.NewStaffData;
import com.sms.dtos.StaffDTO;
import com.sms.exceptions.InvalidProcessingState;
import com.sms.exceptions.ResourceNotFound;
import com.sms.models.Staff;
import com.sms.repositories.StaffRepository;

@Service
public class StaffService {
	private static final Logger lg = LoggerFactory.getLogger(StaffService.class);

	private StaffRepository staffRepo;

	public StaffService(StaffRepository staffRepo) {
		this.staffRepo = staffRepo;
	}

	public void add(NewStaffData staffData) {
		Staff newStaff = new Staff();

		newStaff.setDOJ(LocalDate.now());
		newStaff.setfName(staffData.getfName());
		newStaff.setlName(staffData.getlName());

		staffRepo.save(newStaff);
	}

	public void update(int sn, NewStaffData staffData) {
		Staff staff = getStaff(sn, (short) StaffState.CURRENT.ordinal());

		staff.setfName(staffData.getfName());
		staff.setlName(staffData.getlName());

		staffRepo.save(staff);
		lg.debug("Successfully update {} information", staff);
	}

	public void dismiss(int sn) {
		Staff staff = getStaff(sn, (short) StaffState.CURRENT.ordinal());

		staff.setState((short) StaffState.DISMISSED.ordinal());
		staffRepo.save(staff);
	}

	public List<StaffDTO> getAll(String type) {

		List<Staff> staff = staffRepo.findAll();
		if (type != null) {
			staff = staff.stream().filter(sf -> sf.getType().equalsIgnoreCase(type)).collect(Collectors.toList());
		}

		return staff.stream().map(sf -> staffTODTO(sf)).collect(Collectors.toList());
	}
	

	public List<StaffDTO> getTeachingSorted(String status, String order) {
		List<StaffDTO> staff = getAll(status);
		
		if(order=="doj") {
			Collections.sort(staff, new DOJStaffComparator());
		}
		
		
		return staff;
	}

	private Staff getStaff(int id, short state) {
		Optional<Staff> staff = staffRepo.findById(id);

		if (staff.isPresent()) {
			Staff staffInfo = staff.get();
			if (staffInfo.getState() == state) {
				return staffInfo;
			} else {
				lg.error("Staff's state does not warrant updating their information: {}", staffInfo);
				throw new InvalidProcessingState(String.format("Staff %d", id));
			}
		} else {
			lg.error("Could not find any staff with the id {}", id);
			throw new ResourceNotFound(String.format("Staff %d", id));
		}
	}

	private StaffDTO staffTODTO(Staff staff) {
		StaffDTO sf = new StaffDTO();
		sf.setName(staff.getfName() + " " + staff.getlName());
		sf.setState(staff.getType());
		return sf;
	}

	private class DOJStaffComparator implements Comparator<StaffDTO>  {

		@Override
		public int compare(StaffDTO arg0, StaffDTO arg1) {
			LocalDate d0 = arg0.getDOJ();
			LocalDate d1 = arg1.getDOJ();
			
			return d0.compareTo(d1);
		}
		
	}
}
