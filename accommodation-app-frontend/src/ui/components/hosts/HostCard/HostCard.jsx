import React, { useState } from 'react';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import {
    Box,
    Button,
    Card,
    CardActions,
    CardContent,
    Typography
} from "@mui/material";
import EditHostDialog from "../EditHostDialog/EditHostDialog.jsx";
import DeleteHostDialog from "../DeleteHostDialog/DeleteHostDialog.jsx";

const HostCard = ({ host, onEdit, onDelete, countries }) => {
    const [editHostDialogOpen, setEditHostDialogOpen] = useState(false);
    const [deleteHostDialogOpen, setDeleteHostDialogOpen] = useState(false);

    const countryName = countries?.find(c => c.id === host.country)?.name || 'Unknown';

    return (
        <>
            <Card sx={{ boxShadow: 3, borderRadius: 2, p: 1, width: 300 }}>
                <CardContent>
                    <Typography variant="h5">{host.name} {host.surname}</Typography>
                    <Typography variant="body2" color="text.secondary">Country: {countryName}</Typography>
                </CardContent>
                <CardActions sx={{ justifyContent: "space-between" }}>
                    <Box>
                        <Button
                            size="small"
                            color="warning"
                            startIcon={<EditIcon />}
                            sx={{ mr: "0.25rem" }}
                            onClick={() => setEditHostDialogOpen(true)}
                        >
                            Edit
                        </Button>
                        <Button
                            size="small"
                            color="error"
                            startIcon={<DeleteIcon />}
                            onClick={() => setDeleteHostDialogOpen(true)}
                        >
                            Delete
                        </Button>
                    </Box>
                </CardActions>
            </Card>
            <EditHostDialog
                open={editHostDialogOpen}
                onClose={() => setEditHostDialogOpen(false)}
                host={host}
                countries={countries}
                onEdit={onEdit}
            />
            <DeleteHostDialog
                open={deleteHostDialogOpen}
                onClose={() => setDeleteHostDialogOpen(false)}
                host={host}
                onDelete={onDelete}
            />
        </>
    );
};

export default HostCard;
