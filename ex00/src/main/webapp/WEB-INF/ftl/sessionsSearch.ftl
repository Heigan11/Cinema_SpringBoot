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
            width: 800px;
            height: 30px;
            margin: 200px auto 0;
            background: #60e6c5;
            color: black;
            font-size: 20px;
            outline: none;
            text-transform: uppercase;
            text-align: center;
        }

        table {
            margin: auto;
            /*width: auto;*/
            /*border: 1px solid black;*/
            padding: 3px 5px;
            text-align: center;
        }

    </style>

    <script
            src="https://code.jquery.com/jquery-2.2.4.js"
            integrity="sha256-iT6Q9iMJYuQiMWNd9lDyBUStIq/8PuOW33aOqmvFpqI="
            crossorigin="anonymous"></script>
</head>
<body>

    <div class="formAdd">

    <form name="filmForm">
        <input title="FilmTitle" placeholder="Title" type="text" id="input" size="40" required pattern="^[0-9a-zA-Z]+$">
        <input type="button" value="Find" id="but">
    </form>
    </div>
    <br>
    <br>
    <div id="main">
    <script>
        $("#but").click(function call() {
            $.ajax({
                url: "/sessions/search",
                type: "GET",
                data: {"filmName": $("#input").val()},
                success: function (data) {
                    document.getElementById("main").innerHTML = "";
                    var sessionsArray = JSON.parse(data);
                    table = document.createElement("table");

                    var count = 0;
                    for (var i = 0 ; i < sessionsArray.length / 3; i++){
                        var tr = document.createElement('tr');

                        for (var j = 0; j < 3; j++) {
                            var td = document.createElement('td');
                            var tr1 = document.createElement('tr');
                            var tr2 = document.createElement('tr');
                            var tr3 = document.createElement('tr');
                            if (count < sessionsArray.length) {
                                var img = new Image(180, 130);
                                img.src = "/sessions/search/image/" + sessionsArray[count].film.id;
                                tr1.appendChild(img);
                                tr2.appendChild(document.createTextNode(sessionsArray[count].dateTime));

                                var e    = document.createElement('a');
                                e.href = "/sessions/" + sessionsArray[count].id;
                                e.title = sessionsArray[count].film.name;
                                e.appendChild(document.createTextNode(sessionsArray[count].film.name));
                                tr3.appendChild(e);

                                td.appendChild(tr1);
                                td.appendChild(tr2);
                                td.appendChild(tr3);
                                count++;
                            }
                            tr.appendChild(td);
                        }
                        table.appendChild(tr);
                    }
                    document.getElementById("main").appendChild(table);
                }
            });
        });
    </script>
    </div>

</body>
</html>
