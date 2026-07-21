// Demonstrates preventing a component from rendering: returning null
// makes React skip this component entirely until there is a booking.
function BookingConfirmation({ flight }) {
  if (!flight) {
    return null;
  }

  return (
    <p className="confirmation">
      Ticket booked on {flight.airline} from {flight.from} to {flight.to} at {flight.time}!
    </p>
  );
}

export default BookingConfirmation;
