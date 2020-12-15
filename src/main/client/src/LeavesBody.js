
import { LeaveStatus } from "./LeaveStatus";
import { useHistory } from "react-router-dom";

export const LeavesBody = (props) => {
    const { 
            index,
            leave, 
            onLeavesApproved, 
            onMessages, 
            onApproveLeave 
        } = props;
    const history = useHistory();
    
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
            <td> 
                <LeaveStatus
                    leave = {leave}
                    onApproveLeave = { onApproveLeave }/> 
            </td>
            <td>
                <svg
                    onClick={() => {
                        if(leave.approved.length !== 0) {
                            history.push({
                                pathname: '/leavesApproved',
                                state: { leaveId: leave.id }
                            });
                        } else {
                            onLeavesApproved();
                            onMessages("You don't have any response from for your Supervisor");
                        }
                    }}
                    width="1em" 
                    height="1em" 
                    viewBox="0 0 16 16" 
                    className="bi bi-chevron-right" 
                    fill="currentColor" 
                    xmlns="http://www.w3.org/2000/svg">
                    <path 
                        fillRule="evenodd" 
                        d="M4.646 1.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 0 .708l-6 6a.5.5 0 0 1-.708-.708L10.293 8 4.646 2.354a.5.5 0 0 1 0-.708z"/>
                </svg>
            </td>
        </tr>
    )
}