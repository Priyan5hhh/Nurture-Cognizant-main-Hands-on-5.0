import './App.css';
import CohortDetails from './CohortDetails';

function App() {
  return (
    <div className="App">
      <h1>Cognizant Academy - Cohort Dashboard</h1>
      <CohortDetails
        name="React Fundamentals"
        status="Ongoing"
        startDate="01-Jul-2026"
        endDate="31-Aug-2026"
        mentor="Priyansh Rajput"
      />
      <CohortDetails
        name="Java Full Stack"
        status="Completed"
        startDate="01-Jan-2026"
        endDate="31-Mar-2026"
        mentor="Anita Sharma"
      />
      <CohortDetails
        name="Spring Boot Bootcamp"
        status="Ongoing"
        startDate="15-Jun-2026"
        endDate="15-Sep-2026"
        mentor="Rahul Verma"
      />
      <CohortDetails
        name="Data Structures & Algorithms"
        status="Completed"
        startDate="01-Feb-2026"
        endDate="30-Apr-2026"
        mentor="Neha Gupta"
      />
    </div>
  );
}

export default App;
