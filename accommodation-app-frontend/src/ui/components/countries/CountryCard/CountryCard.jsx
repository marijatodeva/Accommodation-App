import React, {useState} from 'react';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import {Box, Button, Card, CardActions, CardContent, Typography} from "@mui/material";
import DeleteCountryDialog from "../DeleteCountryDialog/DeleteCountryDialog.jsx";
import EditCountryDialog from "../EditCountryDialog/EditCountryDialog.jsx";

const CountryCard = ({country, onEdit, onDelete}) => {
    const [editCountryDialogOpen, setEditCountryDialogOpen] = useState(false);
    const [deleteCountryDialogOpen, setDeleteCountryDialogOpen] = useState(false);

    return (
        <>
            <Card sx={{boxShadow: 3, borderRadius: 2, p: 1}}>
                <CardContent>
                    <Typography variant="h5">{country.name}</Typography>
                    <Typography variant="body1" fontWeight="bold"
                                sx={{textAlign: "right", fontSize: "1.25rem"}}>{country.continent}</Typography>
                </CardContent>
                <CardActions sx={{justifyContent: "flex-end"}}>
                    <Button
                        size="small"
                        color="warning"
                        startIcon={<EditIcon/>}
                        sx={{mr: "0.25rem"}}
                        onClick={() => setEditCountryDialogOpen(true)}
                    >
                        Edit
                    </Button>
                    <Button
                        size="small"
                        color="error"
                        startIcon={<DeleteIcon/>}
                        onClick={() => setDeleteCountryDialogOpen(true)}
                    >
                        Delete
                    </Button>
                </CardActions>
            </Card>

            <EditCountryDialog
                open={editCountryDialogOpen}
                onClose={() => setEditCountryDialogOpen(false)}
                country={country}
                onEdit={onEdit}
            />

            <DeleteCountryDialog
                open={deleteCountryDialogOpen}
                onClose={() => setDeleteCountryDialogOpen(false)}
                country={country}
                onDelete={onDelete}
            />
        </>
    );
};

export default CountryCard;
