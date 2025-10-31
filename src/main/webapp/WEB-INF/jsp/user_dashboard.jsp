<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Dashboard</title>
</head>
<body>
    <h1>User Dashboard</h1>
    <p>Welcome, User!</p>

    <h2>My Repair Orders</h2>
    <div id="orders-list"></div>

    <h2>New Repair Order</h2>
    <form id="new-order-form">
        <div>
            <label for="deviceId">Device ID:</label>
            <input type="number" id="deviceId" name="deviceId" required/>
        </div>
        <div>
            <label for="issueDescription">Issue Description:</label>
            <textarea id="issueDescription" name="issueDescription" minlength="10" required></textarea>
        </div>
        <button type="submit">Create Order</button>
    </form>

    <script>
        fetch("/api/orders").then(res => res.json()).then(data => {
            document.getElementById("orders-list").innerText = JSON.stringify(data, null, 2);
        });

        document.getElementById("new-order-form").addEventListener("submit", function(event) {
            event.preventDefault();
            const formData = new FormData(event.target);
            const data = Object.fromEntries(formData.entries());

            fetch("/api/orders", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(data)
            }).then(res => res.json()).then(newOrder => {
                console.log("Order created:", newOrder);
                // Refresh the list
                fetch("/api/orders").then(res => res.json()).then(data => {
                    document.getElementById("orders-list").innerText = JSON.stringify(data, null, 2);
                });
            });
        });
    </script>
</body>
</html>
