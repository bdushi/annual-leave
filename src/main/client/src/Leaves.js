import { Approved } from "./Approved";

export const Leaves = (props) => {
    const { 
            leaves
        } = props;
    
    return (
        <table className="table table-striped">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Requested By</th>
                <th scope="col">Requested Date</th>
                <th scope="col">Start Date</th>
                <th scope="col">End Date</th>
                <th scope="col">Leave Type</th>
                <th scope="col">Leave Description</th>
                <th scope="col">Leave Comment</th>
                <th scope="col">Approved</th>
		    </tr>
        </thead>
            <tbody>
                { renderTableData(leaves) }
            </tbody>
        </table>
    );
}

function renderTableData(leaves) {
    return leaves.map((leave, index) => {
        return(
            <tr key = { leave.id }>
                <th scope="row">{leave.id}</th>
                <td>{leave.requestedBy.username}</td>
                <td>{new Date(leave.createDate).toLocaleString('en-GB')} </td>
                <td>{new Date(leave.startDate).toLocaleString('en-GB')} </td>
                <td>{new Date(leave.endDate).toLocaleString('en-GB')} </td>
                <td>{leave.leaveTypes.description}</td>
                <td>{leave.description}</td>
                <td>{leave.comment}</td>
                <td> <Approved leaveId = {leave.id} /> </td>
            </tr>
        )
    })
 };