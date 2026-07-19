import React from 'react';

// Demonstrates a handler invoked with an argument via an arrow-function wrapper
function Welcome() {
  const [message, setMessage] = React.useState('');

  const sayWelcome = (name) => {
    setMessage(`${name.charAt(0).toUpperCase() + name.slice(1)}!`);
  };

  return (
    <section className="panel">
      <h2>Welcome Message</h2>
      <button onClick={() => sayWelcome('welcome')}>Say Welcome</button>
      {message && <p className="greeting">{message}</p>}
    </section>
  );
}

export default Welcome;
