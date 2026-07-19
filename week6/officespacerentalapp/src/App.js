import officeImage from './office.svg';
import './App.css';

// Single office object - demonstrates rendering an object's fields via JSX expressions
const office = {
  name: 'Skyline Business Center',
  rent: 75000,
  address: '221B Baker Street, Mumbai',
};

// List of office objects - demonstrates looping through data with .map()
const officeList = [
  { id: 1, name: 'Downtown Co-Work Hub', rent: 45000, address: 'MG Road, Pune' },
  { id: 2, name: 'Skyline Business Center', rent: 75000, address: '221B Baker Street, Mumbai' },
  { id: 3, name: 'Greenfield Executive Suites', rent: 58000, address: 'Whitefield, Bengaluru' },
  { id: 4, name: 'Harbor View Offices', rent: 92000, address: 'Marine Drive, Mumbai' },
  { id: 5, name: 'Riverside Startup Nest', rent: 39000, address: 'Salt Lake, Kolkata' },
];

// Rent below 60000 renders red, at/above 60000 renders green
const rentColor = (rent) => (rent < 60000 ? 'red' : 'green');

function App() {
  return (
    <div className="App">
      <h1>Office Space Rental App</h1>

      <img
        src={officeImage}
        alt="Office space"
        className="office-image"
        width="300"
        height="200"
      />

      <section className="featured-office">
        <h2>Featured Office</h2>
        <p>Name: {office.name}</p>
        <p>
          Rent: <span style={{ color: rentColor(office.rent) }}>₹{office.rent}</span>
        </p>
        <p>Address: {office.address}</p>
      </section>

      <section className="office-listing">
        <h2>Available Office Spaces</h2>
        <ul>
          {officeList.map((item) => (
            <li key={item.id}>
              <strong>{item.name}</strong> — Rent:{' '}
              <span style={{ color: rentColor(item.rent) }}>₹{item.rent}</span> — {item.address}
            </li>
          ))}
        </ul>
      </section>
    </div>
  );
}

export default App;
