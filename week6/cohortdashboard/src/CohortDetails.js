import React from 'react';
import styles from './CohortDetails.module.css';

function CohortDetails(props) {
  const headingColor = props.status.toLowerCase() === 'ongoing' ? 'green' : 'blue';

  return (
    <div className={styles.box}>
      <h3 style={{ color: headingColor }}>{props.name}</h3>
      <dl>
        <dt>Status</dt>
        <dd>{props.status}</dd>

        <dt>Start Date</dt>
        <dd>{props.startDate}</dd>

        <dt>End Date</dt>
        <dd>{props.endDate}</dd>

        <dt>Mentor</dt>
        <dd>{props.mentor}</dd>
      </dl>
    </div>
  );
}

export default CohortDetails;
