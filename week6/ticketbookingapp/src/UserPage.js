import { useState } from 'react';
import FlightList from './FlightList';
import BookingConfirmation from './BookingConfirmation';

function UserPage({ onLogout }) {
  const [bookedFlight, setBookedFlight] = useState(null);

  return (
    <div className="page">
      <h2>Welcome, User - Book Your Flight</h2>
      <button onClick={onLogout}>Logout</button>
      <FlightList canBook={true} onBook={setBookedFlight} />
      <BookingConfirmation flight={bookedFlight} />
    </div>
  );
}

export default UserPage;
