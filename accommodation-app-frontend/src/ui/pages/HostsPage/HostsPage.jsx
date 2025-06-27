import React, {useState} from 'react';
import {Box, Button, CircularProgress} from "@mui/material";
import HostsGrid from "../../components/hosts/HostsGrid/HostsGrid.jsx"
import useHosts from "../../../hooks/useHosts.js";
import "./HostsPage.css"
import AddHostDialog from "../../components/hosts/AddHostDialog/AddHostDialog.jsx";
import useCountries from "../../../hooks/useCountries.js"

const HostsPage = () => {
    const {hosts, loading, onAdd, onEdit, onDelete} = useHosts();
    const [addHostDialog, setAddHostDialogOpen] = useState(false);
    const { countries, loading: countriesLoading } = useCountries();

    return (
        <>
            <Box className="hosts-box">
                {loading && (
                    <Box className="progress-box">
                        <CircularProgress/>
                    </Box>
                )}
                {!loading &&
                    <>
                        <Box sx={{display: "flex", justifyContent: "flex-end", mb: 2}}>
                            <Button variant="contained" color="primary" onClick={() => setAddHostDialogOpen(true)}>
                                Add Host
                            </Button>
                        </Box>
                        <HostsGrid hosts={hosts} countries={countries} onEdit={onEdit} onDelete={onDelete}/>
                    </>}
            </Box>
            <AddHostDialog
                open={addHostDialog}
                onClose={() => setAddHostDialogOpen(false)}
                onAdd={onAdd}
            />
        </>
    );
};

export default HostsPage;