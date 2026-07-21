import flights from './flights';

// Shared by both the Guest and User pages - only the booking action differs
function FlightList({ canBook, onBook }) {
  return (
    <table className="flight-table">
      <thead>
        <tr>
          <th>Airline</th>
          <th>From</th>
          <th>To</th>
          <th>Time</th>
          <th>Price (₹)</th>
          {canBook && <th>Action</th>}
        </tr>
      </thead>
      <tbody>
        {flights.map((flight) => (
          <tr key={flight.id}>
            <td>{flight.airline}</td>
            <td>{flight.from}</td>
            <td>{flight.to}</td>
            <td>{flight.time}</td>
            <td>{flight.price}</td>
            {canBook && (
              <td>
                <button onClick={() => onBook(flight)}>Book</button>
              </td>
            )}
          </tr>
        ))}
      </tbody>
    </table>
  );
}

export default FlightList;
