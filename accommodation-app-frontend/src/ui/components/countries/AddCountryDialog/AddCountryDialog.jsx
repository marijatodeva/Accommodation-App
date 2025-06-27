import React, {useState, useEffect } from 'react';
import {
    Button,
    Dialog,
    DialogActions,
    DialogContent,
    DialogTitle,
    FormControl,
    InputLabel, MenuItem, Select,
    TextField
} from "@mui/material";
import useHosts from "../../../../hooks/useHosts.js";
import useCountries from "../../../../hooks/useCountries.js";
// import accommodationToEdit from "../EditAccommodationDialog/EditAccommodationDialog.jsx"

const initialFormData = {
    "name": "",
    "continent": "",
};

const AddCountryDialog = ({open, onClose, onAdd, onEdit, countryToEdit}) => {
    const [formData, setFormData] = useState(initialFormData);
    // const [categories, setCategories] = useState([]);
    // const hosts = useHosts();


    // useEffect(() => {
    //     fetch("http://localhost:9090/api/accommodations/categories")
    //         .then(res => res.json())
    //         .then(data => {
    //             console.log("Fetched categories:", data); // 👈 Add this
    //             setCategories(data);
    //         })
    //         .catch(err => console.error("Failed to fetch categories:", err));
    // }, []);
    const handleChange = (event) => {
        const {name, value} = event.target;
        setFormData({...formData, [name]: value});
    };

    const handleSubmit = () => {
        onAdd(formData);
        setFormData(initialFormData);
        onClose();
    };

    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Add Country</DialogTitle>
            <DialogContent>
                <TextField
                    id="name"
                    margin="dense"
                    label="Name"
                    name="name"
                    value={formData.name}
                    onChange={handleChange}
                    fullWidth
                />
                <TextField
                    margin="dense"
                    label="Continent"
                    name="continent"
                    value={formData.continent}
                    onChange={handleChange}
                    fullWidth
                />
            </DialogContent>
            <DialogActions>
                <Button onClick={onClose}>Cancel</Button>
                <Button onClick={handleSubmit} variant="contained" color="primary">Add</Button>
            </DialogActions>
        </Dialog>
    );
};

export default AddCountryDialog;