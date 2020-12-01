export const Pagination = (props) => {
    const { 
        currentPage,
        totalPages,
        totalElements,
        numberOfElements,
        onPage,
        onNextPage,
        onPreviousPage
    } = props;

return (
    <nav aria-label="Page navigation example">
        <ul className="pagination justify-content-end">
            <li className = { currentPage <= 0 ? "page-item disabled" : "page-item" }>
                <a onClick={() => onPreviousPage()} className="page-link" href="#">Previous</a>
            </li>
            {
                totalPages.map((page, index) => <li key = {index} className="page-item"><a onClick={() => onPage(index)} className="page-link" href="#">{page}</a></li>)
            }
            <li className = { currentPage >= (totalPages.length - 1) ? "page-item disabled" : "page-item" }>
                <a onClick={() => onNextPage()} className="page-link" href="#">Next</a>
            </li>
        </ul>
    </nav>)
}