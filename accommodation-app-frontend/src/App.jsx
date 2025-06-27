import React from 'react'
import {BrowserRouter, Routes, Route} from "react-router";
import HomePage from './ui/pages/HomePage/HomePage.jsx'
import Layout from "./ui/components/layout/Layout/Layout.jsx";
import AccommodationsPage from "./ui/pages/AccommodationsPage/AccommodationsPage.jsx";
import AccommodationDetails from "./ui/components/accommodations/AccommodationDetails/AccommodationDetails.jsx";
import CountriesPage from "./ui/pages/CountriesPage/CountriesPage.jsx";
import HostsPage from "./ui/pages/HostsPage/HostsPage.jsx";

const App = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Layout/>}>
                    <Route index element={<HomePage/>}></Route>
                    <Route path="accommodations" element={<AccommodationsPage/>}/>
                    <Route path="accommodations/:id" element={<AccommodationDetails/>}/>
                    <Route path="countries" element={<CountriesPage/>}/>
                    <Route path="hosts" element={<HostsPage/>}/>
                </Route>
            </Routes>
        </BrowserRouter>
    );
}
export default App;