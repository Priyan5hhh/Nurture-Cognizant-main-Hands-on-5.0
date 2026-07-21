import { blogs } from './data';

// Conditional rendering with a switch statement
function statusLabel(status) {
  switch (status) {
    case 'published':
      return <span className="status published">Published</span>;
    case 'draft':
      return <span className="status draft">Draft</span>;
    case 'archived':
      return <span className="status archived">Archived</span>;
    default:
      return null;
  }
}

function BlogItem({ blog }) {
  return (
    <li>
      <strong>{blog.title}</strong> — {statusLabel(blog.status)}
      {/* Conditional rendering with the && operator */}
      {blog.isNew && <span className="badge">New</span>}
    </li>
  );
}

function BlogDetails() {
  return (
    <div className="panel">
      <h2>Blog Details</h2>
      <ul>
        {blogs.map((blog) => (
          <BlogItem key={blog.id} blog={blog} />
        ))}
      </ul>
    </div>
  );
}

export default BlogDetails;
