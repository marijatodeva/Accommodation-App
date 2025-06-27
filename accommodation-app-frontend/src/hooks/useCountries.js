import {useCallback, useEffect, useState} from "react";
import countryRepository from "../repository/countryRepository.js";
import hostRepository from "../repository/hostRepository.js";


const initialState = {
    "countries": [],
    "loading": true,
};

const useCountries = () => {
    const [state, setState] = useState(initialState);
    const [countries, setCountries] = useState([]);

    const fetchCountries = useCallback(() => {
        countryRepository
            .findAll()
            .then((response) => {
                setState({
                    "countries": response.data,
                    "loading": false,
                });
            })
            .catch((error) => console.log(error));
    }, []);

    const onAdd = useCallback((data) => {
        countryRepository
            .add(data)
            .then(() => {
                console.log("Successfully added a new country.");
                fetchCountries();
            })
            .catch((error) => console.log(error));
    }, [fetchCountries]);

    const onEdit = useCallback((id, data) => {
        countryRepository
            .edit(id, data)
            .then(() => {
                console.log(`Successfully edited the country with ID ${id}.`);
                fetchCountries();
            })
            .catch((error) => console.log(error));
    }, [fetchCountries]);

    const onDelete = useCallback((id) => {
        countryRepository
            .delete(id)
            .then(() => {
                console.log(`Successfully deleted the country with ID ${id}.`);
                fetchCountries();
            })
            .catch((error) => console.log(error));
    }, [fetchCountries]);

    useEffect(() => {
        fetchCountries();
        console.log(state.countries, state.loading);
    }, [fetchCountries]);

    return {...state, onAdd: onAdd, onEdit: onEdit, onDelete: onDelete};

    //
    // useEffect(() => {
    //     countryRepository
    //         .findAll()
    //         .then((response) => {
    //             setCountries(response.data);
    //         })
    //         .catch((error) => console.log(error));
    // }, []);
};

export default useCountries;