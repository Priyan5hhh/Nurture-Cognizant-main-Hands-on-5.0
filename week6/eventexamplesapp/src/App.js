import './App.css';
import Counter from './Counter';
import Welcome from './Welcome';
import OnPressButton from './OnPressButton';
import CurrencyConvertor from './CurrencyConvertor';

function App() {
  return (
    <div className="App">
      <h1>Event Examples App</h1>
      <Counter />
      <Welcome />
      <OnPressButton />
      <CurrencyConvertor />
    </div>
  );
}

export default App;
