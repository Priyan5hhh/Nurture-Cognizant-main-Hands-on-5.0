import React from 'react';

// Class component - demonstrates the "this" keyword binding for event handlers
class Counter extends React.Component {
  constructor(props) {
    super(props);
    this.state = { count: 0, greeting: '' };

    // Bind handlers so "this" refers to the component instance inside them
    this.handleIncrement = this.handleIncrement.bind(this);
    this.handleDecrement = this.handleDecrement.bind(this);
  }

  incrementCount() {
    this.setState((prevState) => ({ count: prevState.count + 1 }));
  }

  sayHello() {
    this.setState({ greeting: 'Hello, counter incremented!' });
  }

  // Increment button invokes two methods: incrementCount() and sayHello()
  handleIncrement() {
    this.incrementCount();
    this.sayHello();
  }

  handleDecrement() {
    this.setState((prevState) => ({ count: prevState.count - 1, greeting: '' }));
  }

  render() {
    return (
      <section className="panel">
        <h2>Counter</h2>
        <p>Count: {this.state.count}</p>
        <button onClick={this.handleIncrement}>Increment</button>
        <button onClick={this.handleDecrement}>Decrement</button>
        {this.state.greeting && <p className="greeting">{this.state.greeting}</p>}
      </section>
    );
  }
}

export default Counter;
