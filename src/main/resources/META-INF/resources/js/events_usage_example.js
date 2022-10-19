/* When a user is added, do something useful (like update UI) */
EventManager.subscribe('useradded', function(user) {
    console.log(user)
});

/* The UI submits the data, lets publish the event. */
form.onsubmit(function(e) {
    e.preventDefault();

    // do something with user fields

    EventManager.publish('useradded', user);
})