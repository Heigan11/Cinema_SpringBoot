<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Cinema</title>
    <style>
        h2 {
            padding: 10px 0;
            border-width: 0;
            border-radius: 1em;
            display: block;
            width: 1200px;
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
            width: 1200px;
            height: 90px;
            margin: auto;
            background: #60e6c5;
            color: black;
            font-size: 20px;
            outline: none;
            text-transform: uppercase;
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

    <h2>Session info</h2>
    <div class="tables">
        <table width="1200">
            <thead>
            <tr>
                <th>Title</th>
                <th>Year</th>
                <th>Restriction</th>
                <th>Description</th>
                <th>Hall</th>
                <th>Cost</th>
            </tr>
            </thead>
        <tbody>
            <tr>
                <td>${session.movie.title}</td>
                <td>${session.movie.yearOfRelease}</td>
                <td>${session.movie.ageRestrictions}</td>
                <td>${session.movie.description}</td>
                <td>Hall N ${session.hall.id}, ${session.hall.seats} seats</td>
                <td>${session.cost}</td>
            </tr>
        </tbody>
        </table>
    </div>
</body>
</html>