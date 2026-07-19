import React from 'react';

const RUPEES_TO_EURO_RATE = 0.011; // approximate INR -> EUR rate

// Class component - handleSubmit() handles the button's click event and
// performs the rupee-to-euro conversion using "this" to read component state.
class CurrencyConvertor extends React.Component {
  constructor(props) {
    super(props);
    this.state = { rupees: '', euro: null };

    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(event) {
    this.setState({ rupees: event.target.value });
  }

  handleSubmit(event) {
    event.preventDefault();
    const rupees = parseFloat(this.state.rupees) || 0;
    const euro = (rupees * RUPEES_TO_EURO_RATE).toFixed(2);
    this.setState({ euro });
  }

  render() {
    return (
      <section className="panel">
        <h2>Currency Convertor</h2>
        <form onSubmit={this.handleSubmit}>
          <label>
            Rupees (₹):{' '}
            <input
              type="number"
              value={this.state.rupees}
              onChange={this.handleChange}
              placeholder="Enter amount in INR"
            />
          </label>
          <button type="submit">Convert</button>
        </form>
        {this.state.euro !== null && (
          <p className="greeting">
            ₹{this.state.rupees} = €{this.state.euro}
          </p>
        )}
      </section>
    );
  }
}

export default CurrencyConvertor;
