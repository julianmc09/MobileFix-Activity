<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tech Dashboard</title>
</head>
<body>
    <h1>Tech Dashboard</h1>
    <p>Welcome, Technician!</p>

    <h2>Assigned Repair Orders</h2>
    <div id="orders-list"></div>

    <script>
        fetch("/api/orders").then(res => res.json()).then(data => {
            document.getElementById("orders-list").innerText = JSON.stringify(data, null, 2);
        });
    </script>
</body>
</html>
