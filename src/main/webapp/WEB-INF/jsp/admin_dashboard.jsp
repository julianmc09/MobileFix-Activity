<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Dashboard</title>
</head>
<body>
    <h1>Admin Dashboard</h1>
    <p>Welcome, Admin!</p>
    
    <h2>Repair Orders</h2>
    <div id="orders-list"></div>

    <h2>Devices</h2>
    <div id="devices-list"></div>

    <h2>Users</h2>
    <div id="users-list"></div>

    <script>
        // Fetch and display data using the REST APIs
        fetch("/api/orders").then(res => res.json()).then(data => {
            document.getElementById("orders-list").innerText = JSON.stringify(data, null, 2);
        });
        fetch("/api/devices").then(res => res.json()).then(data => {
            document.getElementById("devices-list").innerText = JSON.stringify(data, null, 2);
        });
        fetch("/api/users").then(res => res.json()).then(data => {
            document.getElementById("users-list").innerText = JSON.stringify(data, null, 2);
        });
    </script>
</body>
</html>
