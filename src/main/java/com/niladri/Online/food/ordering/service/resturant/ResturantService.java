package com.niladri.Online.food.ordering.service.resturant;

import com.niladri.Online.food.ordering.dto.resturant.ResturantRequestDto;
import com.niladri.Online.food.ordering.dto.resturant.ResturantResponseDto;
import com.niladri.Online.food.ordering.dto.user.UserResponseDto;
import com.niladri.Online.food.ordering.exception.ResourceNotFound;
import com.niladri.Online.food.ordering.mapper.resturant.ResturantMapper;
import com.niladri.Online.food.ordering.mapper.user.UserMapper;
import com.niladri.Online.food.ordering.model.address.AddressModel;
import com.niladri.Online.food.ordering.model.resturant.ResturantModel;
import com.niladri.Online.food.ordering.model.user.UserModel;
import com.niladri.Online.food.ordering.repository.address.AddressRepository;
import com.niladri.Online.food.ordering.repository.resturant.ResturantRepository;
import com.niladri.Online.food.ordering.repository.user.UserRepository;
import com.niladri.Online.food.ordering.service.resturant.ifc.ResturantServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ResturantService implements ResturantServiceInterface {

	@Autowired
	private ResturantRepository resturantRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private UserRepository userRepository;


	@Override
	public List<ResturantModel> findResturantByName(String keyword) {
		return resturantRepository.findBySearchWord(keyword);
	}

	@Override
	public ResturantResponseDto createResturant(ResturantRequestDto resturantRequest,
	                                            UserResponseDto user) {

		AddressModel address = new AddressModel();
		address.setAddressLine1(resturantRequest.getAddress().getAddressLine1());
		address.setCity(resturantRequest.getAddress().getCity());
		address.setState(resturantRequest.getAddress().getState());
		address.setCountry(resturantRequest.getAddress().getCountry());
		address.setStreetAddress(resturantRequest.getAddress().getStreetAddress());
		address.setZipCode(resturantRequest.getAddress().getZipCode());
		AddressModel savedAddress = addressRepository.save(address);

		ResturantModel resturant = new ResturantModel();
		resturant.setResturantName(resturantRequest.getResturantName());
		resturant.setResturantDescription(resturantRequest.getResturantDescription());
		resturant.setCuisineType(resturantRequest.getCuisineType());
		resturant.setContactInformation(resturantRequest.getContactInformation());
		resturant.setRegistrationDate(resturantRequest.getRegistrationDate());
		resturant.setAddress(savedAddress);
		resturant.setResturantImages(resturantRequest.getResturantImages());
		resturant.setOpeningHours(resturantRequest.getOpeningHours());
		resturant.setRegistrationDate(LocalDateTime.now());
		resturant.setOwner(UserMapper.mapUserResponseDtoToUserModel(user));
		ResturantModel savedResturant = resturantRepository.save(resturant);

		return ResturantMapper.mapResturantModelToResturantResponseDto(savedResturant);
	}

	@Override
	public ResturantModel updateResturant(Long resturantId, ResturantRequestDto resturantRequest) {
		ResturantModel resturant = resturantRepository.findById(resturantId).orElse(null);
		if (resturant == null) {
			throw new ResourceNotFound("Restaurant", "resturantId", resturantId);
		}

		AddressModel savedAddress = new AddressModel();
		if (resturantRequest.getAddress() != null) {
			AddressModel address = resturant.getAddress();
			address.setAddressLine1(resturantRequest.getAddress().getAddressLine1());
			address.setCity(resturantRequest.getAddress().getCity());
			address.setState(resturantRequest.getAddress().getState());
			address.setCountry(resturantRequest.getAddress().getCountry());
			address.setStreetAddress(resturantRequest.getAddress().getStreetAddress());
			address.setZipCode(resturantRequest.getAddress().getZipCode());
			savedAddress = addressRepository.save(address);
		}


		resturant.setResturantName(resturantRequest.getResturantName() != null
				? resturantRequest.getResturantName()
				:  resturant.getResturantName());
		resturant.setResturantDescription(resturantRequest.getResturantDescription() != null
				? resturantRequest.getResturantDescription()
				: resturant.getResturantDescription());
		resturant.setCuisineType(resturantRequest.getCuisineType() != null
				? resturantRequest.getCuisineType()
				: resturant.getCuisineType());
		resturant.setContactInformation(resturantRequest.getContactInformation() != null
				? resturantRequest.getContactInformation()
				: resturant.getContactInformation());
		resturant.setAddress(savedAddress != null
				? savedAddress
				: resturant.getAddress());
		resturant.setResturantImages(resturantRequest.getResturantImages() != null
				? resturantRequest.getResturantImages()
				: resturant.getResturantImages());
		resturant.setOpeningHours(resturantRequest.getOpeningHours() != null
				? resturantRequest.getOpeningHours()
				: resturant.getOpeningHours());
		return resturantRepository.save(resturant);

	}

	@Override
	public void deleteResturant(Long retsurantId) {
		ResturantModel resturant = resturantRepository.findById(retsurantId).orElse(null);
		if (resturant == null) {
			throw new ResourceNotFound("Restaurant", "resturantId", retsurantId);
		}
		resturantRepository.delete(resturant);
	}

	@Override
	public List<ResturantModel> findAllResturants() {
		return resturantRepository.findAll();
	}

	@Override
	public ResturantModel findResturantById(Long resturantId) {
		ResturantModel resturant = resturantRepository.findById(resturantId).orElse(null);
		if (resturant == null) {
			throw new ResourceNotFound("Restaurant", "resturantId", resturantId);
		}
		return resturant;
	}

	@Override
	public ResturantModel findResturantByOwner(Long ownerId) {
		ResturantModel resturant = resturantRepository.findByOwnerUserId(ownerId);
		if(resturant == null){
			throw new ResourceNotFound("Restaurant", "ownerId", ownerId);
		}
		return resturant;
	}

	@Override
	public String addToFavourites(Long resturantId, UserResponseDto user) {
		ResturantModel resturant = findResturantById(resturantId);
		if (resturant == null) {
			throw new ResourceNotFound("Restaurant", "resturantId", resturantId);
		}
		ResturantResponseDto resturantResponseDto = ResturantMapper
										.mapResturantModelToResturantResponseDto(resturant);

		UserModel actualUser = userRepository.findByEmail(user.getEmail());
		List<ResturantResponseDto> favoriteResturant = actualUser.getFavorites();
		boolean isAlreadyFavourite = false;
		for(ResturantResponseDto res : favoriteResturant){
			if(res.getResturantId().equals(resturantId)){
				isAlreadyFavourite = true;
				break;
			}
		}
		if(!isAlreadyFavourite){
			favoriteResturant.add(resturantResponseDto);
		}else{
			favoriteResturant.remove(resturantResponseDto);
		}
		actualUser.setFavorites(favoriteResturant);
		userRepository.save(actualUser);

		return isAlreadyFavourite ? "Resturant removed from favourites" : "Resturant added to favourites";
	}

	@Override
	public String changeResturantStatus(Long resturantId) {
		ResturantModel resturant = findResturantById(resturantId);
		if (resturant == null) {
			throw new ResourceNotFound("Restaurant", "resturantId", resturantId);
		}
		resturant.setOpen(!resturant.isOpen());
		resturantRepository.save(resturant);
		return resturant.isOpen() ? "Resturant is now open" : "Resturant is now closed";
	}


}
