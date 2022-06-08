<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Profile</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <style>
        h2 {
            padding: 10px 0;
            border-width: 0;
            border-radius: 1em;
            display: block;
            width: 500px;
            height: 30px;
            margin: 200px auto 0;
            background: #60e6c5;
            color: black;
            font-size: 25px;
            outline: none;
            text-transform: uppercase;
            text-align: center;
        }
        .formAdd {
            padding: 5px 0;
            border-width: 0;
            border-radius: 1em;
            display: block;
            width: 500px;
            height: 90px;
            margin: auto;
            background: #60e6c5;
            text-align: center;
        }

        table {
            margin: auto; /* Выравниваем таблицу по центру */
        }
        .tables {
            width: auto;
            border: none;
            margin: auto;
            border-collapse: separate;
            text-align: center;
        }
        .tables thead th {
            font-weight: bold;
            text-align: center;
            border: none;
            padding: 10px 15px;
            background: #EDEDED;
            font-size: 14px;
            border-top: 1px solid #ddd;
        }
        .tables tr th:first-child, .table tr td:first-child {
            border-left: 1px solid #ddd;
        }
        .tables tr th:last-child, .table tr td:last-child {
            border-right: 1px solid #ddd;
        }
        .tables thead tr th:first-child {
            border-radius: 20px 0 0 0;
        }
        .tables thead tr th:last-child {
            border-radius: 0 20px 0 0;
        }
        .tables tbody td {
            text-align: center;
            border: none;
            padding: 10px 15px;
            font-size: 14px;
            vertical-align: top;
        }
        .tables tbody tr:nth-child(even) {
            background: #F8F8F8;
        }
        .tables tbody tr:last-child td{
            border-bottom: 1px solid #ddd;
        }
        .tables tbody tr:last-child td:first-child {
            border-radius: 0 0 0 20px;
        }
        .tables tbody tr:last-child td:last-child {
            border-radius: 0 0 20px 0;
        }
    </style>
</head>

<body>
<div class="w3-panel" style="display:flex;justify-content: center;flex-wrap: wrap">
    <div style="flex: 0 0 20%; padding-right: 10px">
        <img src='/profile/image/${user.id}' width="300" class="w3-image w3-round">
        <form method="post" action="/uploadAvatar/${user.id}" enctype="multipart/form-data">
            <br>
            <input type="file" name="file" class="w3-input">
            <button type="submit" class="w3-button w3-block w3-cyan w3-ripple w3-padding">Upload</button>
        </form>
    </div>

    <div style="flex: 0 0 40%;">
        <h3>
            ${user.name}
        </h3>

        <table class="w3-table w3-bordered w3-centered w3-card">
            <thead>
            <tr class="w3-cyan">
                <th>Date</th>
                <th>Time</th>
                <th>IP</th>
            </tr>
            </thead>
            <tbody>
                <#list usersessions as sess>
                <tr>
                    <td>${sess.date}</td>
                    <td>${sess.time}</td>
                    <td>${sess.ip}</td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>

    <table class="w3-table w3-bordered w3-centered w3-card" style="width: 60%">
        <thead>
        <tr class="w3-cyan">
            <th>File name</th>
            <th>Size</th>
            <th>MIME</th>
            <th>Set as avatar</th>
        </tr>
        </thead>
        <tbody>
        <#list images as img>
            <tr>
                <td> <a href="./images/${user.id}/${img.uniqueName}" target="_blank">${img.originalName}</a></td>
                <td>${img.size}</td>
                <td>${img.mimetype}</td>
                <td>
                    <form method="post" action="setImage/${user.id}">
                        <div class="w3-padding" align="center">
                            <button type="submit"
                                    class="w3-button w3-block w3-cyan w3-ripple"
                                    value="${img.id}" name="img">set
                            </button>
                        </div>
                    </form>
                </td>
            </tr>
        </#list>

        </tbody>
    </table>

    <form method="get" action="logout" style="width: 60%">
        <div class="w3-padding">
            <button type="submit" class="w3-button w3-block w3-section w3-pale-blue w3-ripple w3-padding">
                Logout
            </button>
        </div>
    </form>
</div>

</body>
</html>
