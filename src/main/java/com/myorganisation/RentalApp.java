package com.myorganisation;

import static com.myorganisation.constants.Discount.BUS_DISCOUNT_PERCENTAGE;
import static com.myorganisation.constants.Fare.DISCOUNTED_FARE_FOR_DIESEL_VEHICLE;
import static com.myorganisation.constants.Fare.EXTRA_FARE_FOR_AC_VEHICLE;
import static com.myorganisation.model.FuelType.DIESEL;
import static com.myorganisation.model.VehicleModel.BUS;
import static com.myorganisation.model.VehicleType.AC;

import com.myorganisation.constants.PassengersCapacityPerVehicle;
import com.myorganisation.model.FuelType;
import com.myorganisation.model.VehicleModel;
import com.myorganisation.model.VehicleType;

/**
 * Main class for Rental App API
 */
public class RentalApp {

    private double standardRate;

    /**
     * standard rate to be set while initialisation
     *
     * @param standardRate
     */
    public RentalApp(double standardRate) {
        this.standardRate = standardRate;
    }

    /**
     * Calculate trip cost for the given vehicle model, fuel type, vehicle type, total distance and number of passengers
     *
     * @param vehicleModel       - model of the vehicle for eg. SUV, CAR, VAN, BUS
     * @param fuelType           - fuel type for eg. AC, NONAC
     * @param vehicleType        - vehicle type for eg. PETROL, DIESEL
     * @param distance           - total distance in Kms, for eg. 2000
     * @param numberOfPassengers - total number of passengers for eg. 5
     * @return tripCost
     */
    public double tripCost(VehicleModel vehicleModel, FuelType fuelType, VehicleType vehicleType, double distance,
                           int numberOfPassengers) {

        double rate = standardRate;

        if (vehicleType == AC) {
            rate += EXTRA_FARE_FOR_AC_VEHICLE;
        }

        if (fuelType == DIESEL) {
            rate -= DISCOUNTED_FARE_FOR_DIESEL_VEHICLE;
        }

        if (vehicleModel == BUS) {
            rate -= ((rate * BUS_DISCOUNT_PERCENTAGE) / 100);
        }

        rate = getFinalRateIfPassengersExccededLimit(vehicleModel, rate, numberOfPassengers);

        return (rate * distance);
    }

    private double getFinalRateIfPassengersExccededLimit(VehicleModel vehicleModel, double rate,
                                                         int numberOfPassengers) {

        double finalRate = rate;

        switch (vehicleModel) {
            case SUV:
                if (numberOfPassengers > PassengersCapacityPerVehicle.SUV_LIMIT) {
                    finalRate += numberOfPassengers;
                }
                break;
            case CAR:
                if (numberOfPassengers > PassengersCapacityPerVehicle.CAR_LIMIT) {
                    finalRate += numberOfPassengers;
                }
                break;
            case VAN:
                if (numberOfPassengers > PassengersCapacityPerVehicle.VAN_LIMIT) {
                    finalRate += numberOfPassengers;
                }
                break;
            case BUS:
                if (numberOfPassengers > PassengersCapacityPerVehicle.BUS_LIMIT) {
                    finalRate += numberOfPassengers;
                }
                break;
        }

        return finalRate;
    }

}
