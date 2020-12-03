export const Alert = (props) => {
    const { messages,  visibility, onAlertClose} = props;
    return(
        <div style = {
            {
                position: "fixed",
                bottom: 0,
                right: 0,
                marginRight: 20
            }
        } className={visibility} role="alert">
            { messages }
            <button 
                type="button" 
                className="btn-close" 
                data-dismiss="alert" 
                aria-label="Close"
                onClick={() => onAlertClose()}/>
        </div>
    )
}