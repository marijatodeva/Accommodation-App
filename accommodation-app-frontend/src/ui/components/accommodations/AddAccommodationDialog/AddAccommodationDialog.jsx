import React, { useState, useEffect } from 'react';
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

const initialFormData = {
    "name": "",
    "category": "",
    "hostId": "",
    "numRooms": "",
};

const AddAccommodationDialog = ({ open, onClose, onAdd }) => {
    const [formData, setFormData] = useState(initialFormData);
    const [categories, setCategories] = useState([]);
    const {hosts} = useHosts();


    useEffect(() => {
        fetch("http://localhost:9090/api/accommodations/categories")
            .then(res => res.json())
            .then(data => setCategories(data))
            .catch(err => console.error("Failed to fetch categories:", err));
    }, []);

    const handleChange = (event) => {
        const { name, value } = event.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = () => {
        onAdd(formData);
        setFormData(initialFormData);
        onClose();
    };

    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Add Accommodation</DialogTitle>
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
                <FormControl fullWidth margin="dense">
                    <InputLabel>Category</InputLabel>
                    <Select
                        name="category"
                        value={formData.category}
                        onChange={handleChange}
                        label="Category"
                    >
                        {categories.map((cat, index) => (
                            <MenuItem key={index} value={cat}>{cat}</MenuItem>
                        ))}
                    </Select>
                </FormControl>

                <TextField
                    margin="dense"
                    label="NumRooms"
                    name="numRooms"
                    type="number"
                    value={formData.numRooms}
                    onChange={handleChange}
                    fullWidth
                />
                <FormControl fullWidth margin="dense">
                    <InputLabel>Author</InputLabel>
                    <Select
                        name="hostId"
                        value={formData.hostId}
                        onChange={handleChange}
                        label="Host"
                        variant="outlined">
                        {hosts.map((hostId) => (
                            <MenuItem key={hostId.id} value={hostId.id}>{hostId.name}</MenuItem>
                        ))}
                    </Select>
                </FormControl>
            </DialogContent>
            <DialogActions>
                <Button onClick={onClose}>Cancel</Button>
                <Button onClick={handleSubmit} variant="contained" color="primary">Add</Button>
            </DialogActions>
        </Dialog>
    );
};

export default AddAccommodationDialog;
