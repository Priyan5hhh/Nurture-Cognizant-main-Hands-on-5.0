import { render, screen } from '@testing-library/react';
import App from './App';

test('renders office space rental heading', () => {
  render(<App />);
  const headingElement = screen.getByText(/office space rental app/i);
  expect(headingElement).toBeInTheDocument();
});
