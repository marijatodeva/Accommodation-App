import {useCallback, useEffect, useState} from "react";
import hostRepository from "../repository/hostRepository.js";

const initialState = {
    hosts: [],
    loading: true,
}

export const useHosts = () => {
    const [state, setState] = useState(initialState)

    const fetchHosts = useCallback(() => {
        hostRepository
            .findAll()
            .then(response => {
                setState({
                    hosts: response.data,
                    loading: false,
                })
            })
            .catch(error => console.log(error))
    }, [])

    const onAdd = useCallback((data) => {
        hostRepository
            .add(data)
            .then(() => {
                fetchHosts()
            })
            .catch(error => console.log(error))
    }, [fetchHosts])

    const onEdit = useCallback((id, data) => {
        hostRepository
            .edit(id, data)
            .then(() => {
                fetchHosts()
            })
            .catch(error => console.log(error))
    }, [fetchHosts])

    const onDelete = useCallback((id) => {
        hostRepository.delete(id)
            .then(() => {
                fetchHosts()
            })
            .catch(error => console.log(error))
    }, [fetchHosts])

    useEffect(() => {
        fetchHosts()
    }, [fetchHosts]);

    return {...state, onAdd: onAdd, onEdit: onEdit, onDelete: onDelete};
}

export default useHosts;