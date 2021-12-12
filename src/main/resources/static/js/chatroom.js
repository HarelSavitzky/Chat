(function () {

    //refresh chat function, polling mechanism is applied(every 10 seconds),
    // will fetch the updated list of messages/ online users from db.
    function refreshChat() {
        fetch("/getMessages", {
        })
            .then(res => res.json())
            .then(resp => {
                res = "";
                for (el of resp) {
                    res = res  + "<div class=\"msg_cotainer\" style=\"margin:5px; display: inline;\" ><b>"
                        + el.author + "</b>" + " : " + el.msg + "</div></br></br>";

                }
                document.getElementById("messages").innerHTML = " ";
                document.getElementById("messages").innerHTML = res;
            })
            .catch(e => {
                document.getElementById("messages").innerHTML = "Some error occurred!, error: " + e;
            });

        fetch("/getUsers", {
        })
            .then(res => res.json())
            .then(resp => {
                res = "";
                for (el of resp) {
                    res = res + '<li>' + el.userName +'</li>';
                }
                document.getElementsByTagName("UL")[0].innerHTML = "";
                document.getElementById("connected").innerHTML = res ;
            })
            .catch(e => {
                document.getElementById("connected").innerHTML = "Some error occurred!, error: " + e;
            });
    }

    //modal function
    function openDialog() {
        $('#errorModal').modal('show');
    }

    //fetch message function, send current message to db and fetch the updated list of messages.
    function fetchMessage() {

        let msg = document.getElementById("msg").value;
        document.getElementById("msg").value = "";

        if (msg.trim() === "") {
            openDialog();
        } else {

            fetch("/message", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },body: (msg),
            })
                .then(res => res.json())
                .then(resp => {
                    res = "";
                    for (el of resp) {
                        res = res  + "<div class=\"msg_cotainer\" style=\"margin:5px; display: inline;\" ><b>"
                            + el.author + "</b>" + " : " + el.msg + "</div></br></br>";

                    }
                    document.getElementById("messages").innerHTML = " ";
                    document.getElementById("messages").innerHTML = res;
                })
                .catch(e => {
                    document.getElementById("messages").innerHTML = "Some error occurred!, error: " + e;
                });
        }
    }

    //search by function, receives event(by author/message) and fetch the relevant data.
    function searchBy(event) {

        let input = document.getElementById("search").value;
        let type;

        if(input.trim() === ""){
            openDialog();
        }

        if(event.target.innerText  === "Find By Author"){
            type = "Author";
        }
        else if(event.target.innerText === "Find By Message"){
            type = "Message";
        }

        fetch("/findBy" + type , {
            method: 'POST',
            headers: {
                'Content-Type': 'text/plain',
            },body: input,
        })
            .then(res => res.json())
            .then(resp => {
                res = "";
                for (el of resp) {
                    res = res  + "<div class=\"msg_cotainer\" style=\"margin:5px; display: inline;\" ><b>"
                        + el.author + "</b>" + " : " + el.msg + "</div></br></br>";
                }
                document.getElementById("sbody").innerHTML = res;
            })
            .catch(e => {
                document.getElementById("sbody").innerHTML = "Some error occured!, error: " + e;
            });
    }

    document.addEventListener('DOMContentLoaded', function () {
        setInterval(()=> {
            refreshChat();
        }, 10 * 1000); // polling mechanism every 10 seconds

        document.getElementById("btn").addEventListener("click", fetchMessage);
        document.getElementById("findByAuthor").addEventListener("click", searchBy);
        document.getElementById("findByMessage").addEventListener("click", searchBy);
        document.addEventListener("keyup", function(event) {if (event.keyCode === 13) {fetchMessage();}});
    });

})();
