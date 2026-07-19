import { render, screen } from '@testing-library/react';
import App from './App';

test('renders event examples app heading', () => {
  render(<App />);
  const headingElement = screen.getByText(/event examples app/i);
  expect(headingElement).toBeInTheDocument();
});
