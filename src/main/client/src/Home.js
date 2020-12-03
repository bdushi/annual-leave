
import axios from "axios"
import React, { useState, useEffect } from "react";
import { Leaves } from "./Leaves";
import { Pagination } from "./Pagination";

function Home(props) {
    const { search } = props;
    const [leaves, setLeaves] = useState([])
    const [totalPages, setTotalPages] = useState([])
    const [totalElements, setTotalElements] = useState()
    const [numberOfElements, setNumberOfElements] = useState(0)
    
    const [currentPage, setCurrentPage] = useState(0);
    
    const page = (pageNum) => {
        setCurrentPage(pageNum);
    };

    const nextPage = () => {
        if(currentPage < totalPages.length) {
            setCurrentPage(currentPage + 1);
        }
    };

    const previousPage = () => {
        setCurrentPage(currentPage - 1);
        // if(currentPage <= 0) {
        //     setCurrentPage(currentPage - 1);
        // }
    };

    useEffect(() => {
            axios
                .get(`/leave?search=${search}&page=${currentPage}&size=10`)
                .then(function (response) {
                    setLeaves(response.data.content)
                    // setTotalPages([...Array(response.data.totalPages).keys()])
                    setTotalPages(Array.from({length: response.data.totalPages}, (_, i) => i + 1))
                    setTotalElements(response.data.totalElements)
                    setNumberOfElements(response.data.numberOfElements)
                }).catch((error) => {
                    console.log(error)
                });
        }
        ,[currentPage]
    )

    return(
        <div className="container">
            <Leaves 
                leaves = { leaves }/>
            <Pagination 
                currentPage = {currentPage}
                totalPages = {totalPages} 
                totalElements = {totalElements} 
                numberOfElements = {numberOfElements}
                onPage = { page }
                onNextPage = { nextPage }
                onPreviousPage= { previousPage }/>
        </div>
    )
}

export default Home;