package com.myorganisation;

import static com.myorganisation.constants.Distance.MUMBAI_TO_BANGALORE;
import static com.myorganisation.constants.Distance.MUMBAI_TO_CHENNAI;
import static com.myorganisation.constants.Distance.MUMBAI_TO_DELHI;
import static com.myorganisation.constants.Distance.PUNE_TO_BANGALORE;
import static com.myorganisation.constants.Distance.PUNE_TO_MUMBAI;
import static com.myorganisation.model.FuelType.DIESEL;
import static com.myorganisation.model.FuelType.PETROL;
import static com.myorganisation.model.VehicleModel.BUS;
import static com.myorganisation.model.VehicleModel.CAR;
import static com.myorganisation.model.VehicleModel.SUV;
import static com.myorganisation.model.VehicleModel.VAN;
import static com.myorganisation.model.VehicleType.AC;
import static com.myorganisation.model.VehicleType.NONAC;
import static junit.framework.TestCase.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RentalAppTest {

	RentalApp rentalApp;

	@Before
	public void setUp() {
		// Given
		rentalApp = new RentalApp(15);
	}

	@Test
	public void tripCostForSwiftDieselNonACPuneMumbaiBangalorePuneLimitedPassengers() {
		// When
		double totalDistanceTravelled = PUNE_TO_MUMBAI + MUMBAI_TO_BANGALORE + PUNE_TO_BANGALORE;
		int numberOfPassengers = 3;

		// Then
		assertEquals("inaccurate trip cost", 28000.0,
			rentalApp.tripCost(CAR, DIESEL, NONAC, totalDistanceTravelled, numberOfPassengers));
	}

	@Test
	public void tripCostForSwiftDieselNonACPuneMumbaiPassengersLimitExceeded() {
		// When
		double totalDistanceTravelled = PUNE_TO_MUMBAI;
		int numberOfPassengers = 6;

		// Then
		assertEquals("inaccurate trip cost", 4000.0,
			rentalApp.tripCost(CAR, DIESEL, NONAC, totalDistanceTravelled, numberOfPassengers));
	}

	@Test
	public void tripCostForVolvoDieselACPuneMumbaiPuneLimitedPassengers() {
		// When
		double totalDistanceTravelled = 2 * PUNE_TO_MUMBAI;
		int numberOfPassengers = 36;

		// Then
		assertEquals("inaccurate trip cost", 6272.0,
			rentalApp.tripCost(BUS, DIESEL, AC, totalDistanceTravelled, numberOfPassengers));
	}

	@Test
	public void tripCostForBMWPetrolACMumbaiDelhiPassengersLimitMaximised() {
		// When
		double totalDistanceTravelled = MUMBAI_TO_DELHI;
		int numberOfPassengers = 7;

		// Then
		assertEquals("inaccurate trip cost", 34850.0,
			rentalApp.tripCost(SUV, PETROL, AC, totalDistanceTravelled, numberOfPassengers));
	}

	@Test
	public void tripCostForToyotaPetrolNonACMumbaiChennaiPassengersLimitMaximised() {
		// When
		double totalDistanceTravelled = MUMBAI_TO_CHENNAI;
		int numberOfPassengers = 10;

		// Then
		assertEquals("inaccurate trip cost", 18517.5,
			rentalApp.tripCost(VAN, PETROL, NONAC, totalDistanceTravelled, numberOfPassengers));
	}

	@After
	public void tearDown() {
		rentalApp = null;
	}
}
