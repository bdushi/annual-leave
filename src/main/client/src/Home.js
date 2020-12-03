
import axios from "axios"
import React, { useState, useEffect, useContext } from "react";
import { Leaves } from "./Leaves";
import { Pagination } from "./Pagination";
import { SearchContext } from "./context/SearchContext";

function Home() {
    const [leaves, setLeaves] = useState([])
    const [totalPages, setTotalPages] = useState([])
    const [totalElements, setTotalElements] = useState()
    const [numberOfElements, setNumberOfElements] = useState(0)
    const [visibility, setVisibility] = useState(false)
    const [messages, setMessages] = useState()
    
    const [currentPage, setCurrentPage] = useState(0);

    const { search } = useContext(SearchContext);
    
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
                    setVisibility(true)
                    setMessages(error)
                });
        }
        ,[currentPage, search]
    )

    return(
        <section>
            <div className="container">
            <Leaves 
                visibility = { visibility }
                messages = { messages }
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
        </section>
    )
}

export default Home;