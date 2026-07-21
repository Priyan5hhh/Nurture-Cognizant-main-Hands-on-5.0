import FlightList from './FlightList';

function GuestPage({ onLogin }) {
  return (
    <div className="page">
      <h2>Guest - Browse Flights</h2>
      <p>Log in to book tickets.</p>
      <button onClick={onLogin}>Login</button>
      <FlightList canBook={false} />
    </div>
  );
}

export default GuestPage;
