import React from 'react';
import { useNavigate, useParams } from "react-router";
import useAccommodationDetails from "../../../../hooks/useAccommodationDetails.js";
import {
    Box,
    CircularProgress,
    Typography,
    Paper,
    Avatar,
    Stack,
    Rating,
    Breadcrumbs,
    Link,
    Chip,
    Grid,
    Button,
    Divider
} from "@mui/material";
import {
    ArrowBack,
    Category as CategoryIcon,
} from "@mui/icons-material";

const AccommodationDetails = () => {
    const navigate = useNavigate();
    const { id } = useParams();
    const { accommodation, host } = useAccommodationDetails(id);

    if (!accommodation || !host) {
        return (
            <Box sx={{ display: 'flex', justifyContent: 'center', alignItems: 'center', minHeight: '60vh' }}>
                <CircularProgress />
            </Box>
        );
    }

    return (
        <Box>
            <Breadcrumbs aria-label="breadcrumb" sx={{ mb: 3 }}>
                <Link
                    underline="hover"
                    color="inherit"
                    href="#"
                    onClick={(e) => {
                        e.preventDefault();
                        navigate("/accommodations");
                    }}
                >
                    Accommodations
                </Link>
                <Typography color="text.primary">{accommodation.name}</Typography>
            </Breadcrumbs>

            <Paper elevation={2} sx={{ p: 4, borderRadius: 4 }}>
                <Grid container spacing={4}>
                    <Grid item xs={12} md={3}>
                        <Avatar
                            src={accommodation.image || "/placeholder-accommodation.jpg"}
                            variant="rounded"
                            sx={{
                                width: '100%',
                                height: 200,
                                objectFit: 'cover',
                                borderRadius: 2
                            }}
                        />
                    </Grid>

                    <Grid item xs={12} md={9}>
                        <Typography variant="h4" gutterBottom sx={{ fontWeight: 600 }}>
                            {accommodation.name}
                        </Typography>

                        <Stack direction="row" spacing={1} alignItems="center" sx={{ mb: 2 }}>
                            <Rating
                                value={accommodation.avgRating / 2}
                                precision={0.5}
                                readOnly
                            />
                            <Typography variant="body2" color="text.secondary">
                                ({accommodation.reviewList?.length || 0} reviews)
                            </Typography>
                        </Stack>

                        <Typography variant="body2" color="text.secondary" sx={{ mb: 3 }}>
                            Average Rating: {(accommodation.avgRating).toFixed(1)} / 10
                        </Typography>

                        <Stack direction="row" spacing={1} sx={{ mb: 3 }}>
                            <Chip
                                icon={<CategoryIcon />}
                                label={accommodation.category}
                                color="primary"
                                variant="outlined"
                                sx={{ fontSize: '1rem' }}
                            />
                        </Stack>

                        <Typography variant="body1" color="text.secondary">
                            Total Rooms: {accommodation.numRooms}
                        </Typography>
                        <Typography variant="body1" color="text.secondary">
                            Currently Rented: {accommodation.isRented ? 'Yes' : 'No'}
                        </Typography>
                        <Typography variant="body2" color="text.secondary" sx={{ mt: 2 }}>
                            Host: {host?.name || 'Unknown'}
                        </Typography>
                    </Grid>
                </Grid>

                <Divider sx={{ my: 4 }} />

                <Typography variant="h5" sx={{ mb: 2 }}>
                    Reviews
                </Typography>

                {accommodation.reviewList && accommodation.reviewList.length > 0 ? (
                    accommodation.reviewList.map((review, index) => (
                        <Box key={index} sx={{ mb: 3, p: 2, borderRadius: 2, bgcolor: 'background.paper', boxShadow: 1 }}>
                            <Stack direction="row" spacing={1} alignItems="center" sx={{ mb: 1 }}>
                                <Rating
                                    value={review.rate / 2}
                                    precision={0.5}
                                    readOnly
                                    size="small"
                                />
                                <Typography variant="body2" color="text.secondary">
                                    Rating: {review.rate}
                                </Typography>
                            </Stack>
                            <Typography variant="body1">{review.comment}</Typography>
                        </Box>
                    ))
                ) : (
                    <Typography color="text.secondary">No reviews yet.</Typography>
                )}

                <Box display="flex" justifyContent="flex-end" sx={{ mt: 4 }}>
                    <Button
                        variant="outlined"
                        startIcon={<ArrowBack />}
                        onClick={() => navigate("/accommodations")}
                    >
                        Back to Accommodations
                    </Button>
                </Box>
            </Paper>
        </Box>
    );
};

export default AccommodationDetails;
