import React from "react";
import { Grid } from "@mui/material";
import HostCard from "../HostCard/HostCard.jsx";
import useCountries from "../../../../hooks/useCountries.js"

const HostsGrid = ({ hosts = [], onEdit, onDelete, countries }) => {
    return (
        <Grid container spacing={{ xs: 2, md: 3 }}>
            {hosts.map((host) => (
                <Grid key={host.id} item xs={12} sm={6} md={4} lg={3}>
                    <HostCard host={host} countries={countries} onEdit={onEdit} onDelete={onDelete} />
                </Grid>
            ))}
        </Grid>
    );
};

export default HostsGrid;
