import React from 'react';
import styles from './style.module.css'

const DepartmentLabel = ({ department }) => {
    return (
        <div className={styles.departmentLabel}>
            <div className={styles.departmentLabelHeader}>
                <p className={styles.departmentLabelHeaderText}>
                    {`Отдел ГАИ ${department.locality} ${department.address}`}
                </p>
            </div>
            <p className={styles.departmentLabelText}>
                {`Сотрудников: ${department.employeesCount}`}
            </p>
            <p className={styles.departmentLabelText}>
                {`Технических инспекций: ${department.inspectionsCount}`}
            </p>
        </div>
    );
};

export default DepartmentLabel;