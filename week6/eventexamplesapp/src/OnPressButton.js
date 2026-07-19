import React from 'react';

// Demonstrates React's SyntheticEvent - a cross-browser wrapper around the
// browser's native event, passed automatically to every event handler.
function OnPressButton() {
  const [clicked, setClicked] = React.useState(false);

  const handlePress = (syntheticEvent) => {
    // syntheticEvent is a React SyntheticEvent, not the native DOM event
    console.log('Event type:', syntheticEvent.type);
    setClicked(true);
  };

  return (
    <section className="panel">
      <h2>Synthetic Event</h2>
      <button onClick={handlePress}>OnPress</button>
      {clicked && <p className="greeting">I was clicked</p>}
    </section>
  );
}

export default OnPressButton;
