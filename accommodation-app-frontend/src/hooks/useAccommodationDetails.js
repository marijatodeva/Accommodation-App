import {useEffect, useState} from "react";
import hostRepository from "../repository/hostRepository.js";
import countryRepository from "../repository/countryRepository.js";
import accommodationRepository from "../repository/accommodationRepository.js";

const useAccommodationDetails = (id) => {
    const [state, setState] = useState({
        "accommodation": null,
        "country": null,
        "host": null,
    });

    useEffect(() => {
        accommodationRepository
            .findById(id)
            .then((response) => {
                setState(prevState => ({...prevState, "accommodation": response.data}));
                hostRepository
                    .findById(response.data.hostId)
                    .then((response) => {
                        setState(prevState => ({...prevState, "host": response.data}));
                    })
                    .catch((error) => console.log(error));
                countryRepository
                    .findById(response.data.countryId)
                    .then((response) => {
                        setState(prevState => ({...prevState, "country": response.data}));
                    })
                    .catch((error) => console.log(error));
            })
            .catch((error) => console.log(error));
    }, [id]);

    return state;
};

export default useAccommodationDetails;