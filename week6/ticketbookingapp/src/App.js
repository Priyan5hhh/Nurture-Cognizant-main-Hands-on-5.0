import { useState } from 'react';
import './App.css';
import GuestPage from './GuestPage';
import UserPage from './UserPage';

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  // Element variable: decide which JSX to render, then render the variable once
  let page;
  if (isLoggedIn) {
    page = <UserPage onLogout={() => setIsLoggedIn(false)} />;
  } else {
    page = <GuestPage onLogin={() => setIsLoggedIn(true)} />;
  }

  return (
    <div className="App">
      <h1>Ticket Booking App</h1>
      {page}
    </div>
  );
}

export default App;
