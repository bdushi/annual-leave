import axios from "axios"
import { SearchContext } from "./context/SearchContext";
import React, { useState, useEffect, useContext } from "react";
import { Alert } from "./Alert";

import { Pagination } from "./Pagination";

export const LeavesApproved = (props) => {
    const { leaveId } = props.location.state;
    const [approved, setApproved] = useState([])

    const [currentPage, setCurrentPage] = useState(0);
    const [totalPages, setTotalPages] = useState([])
    const [totalElements, setTotalElements] = useState()
    const [numberOfElements, setNumberOfElements] = useState(0)

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
            .get(`/approved?id=${leaveId}&search=${search}&page=${currentPage}&size=10`)
            .then(function (response) {
                setApproved(response.data.content)
                setTotalPages(Array.from({length: response.data.totalPages}, (_, i) => i + 1))
                setTotalElements(response.data.totalElements)
                setNumberOfElements(response.data.numberOfElements)
            }).catch((error) => {
                console.log(error)
            });
    }
    ,[leaveId, search, currentPage]
)
    return(
        <section>
            <table className="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Response Date</th>
                        <th scope="col">Comment</th>
                        <th scope="col">Status</th>
                        <th scope="col">Responsed</th>
                    </tr>
                </thead>
                    <tbody>
                        {
                            approved.map((approve, index) => {
                                return(
                                    <tr key = { approve.id }>
                                        <th scope="row">{approve.id}</th>
                                        <td>{new Date(approve.approvedDate).toLocaleString('en-GB')} </td>
                                        <td>{approve.comment}</td>
                                        <td>{approve.approved ? "Approved" : "Not Approved"}</td>
                                        <td>{approve.approvedBy.username}</td>
                                    </tr>
                                )
                            })
                        }
                    </tbody>
            </table> 
            <Pagination 
                currentPage = {currentPage}
                totalPages = {totalPages} 
                totalElements = {totalElements} 
                numberOfElements = {numberOfElements}
                onPage = { page }
                onNextPage = { nextPage }
                onPreviousPage= { previousPage }/>
        </section>
        
    )
}