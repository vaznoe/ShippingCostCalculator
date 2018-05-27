addEventListener("DOMContentLoaded", () => {
    document.forms["shipping-form"].addEventListener("submit", submit)
});

function submit(event) {
    console.log("this form submitted successful");
    event.preventDefault();
    const shipmentDate = document.forms["shipping-form"]["shipment-date"].value
    const distance = document.forms["shipping-form"]["distance"].value
    const weight = document.forms["shipping-form"]["weight"].value
    fetch("http://localhost:8080/shippingCost/calculate", {
        credentials: "same-origin",
        method:"POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({shipmentDate, distance, weight})
    }).then(s => {
            console.log(s);});
}
