window.onload = function (){
    let decks = document.getElementsByClassName("deck");
    Array.from(decks).forEach(deck => {
        deck.addEventListener("click", () => {
            let _userId = $("#userId").val();
            let _deckId = deck.id;
            $.post("choose-deck", {
                userId: _userId,
                deckId: _deckId
            });
            window.location.replace("/home");
        });
    });
};