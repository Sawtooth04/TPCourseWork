import React, {useState} from 'react';
import styles from './style.module.css';
import IconButton from "../UI/IconButton/IconButton";
import InfiniteScrollPaginator from "../UI/InfiniteScrollPaginator/InfiniteScrollPaginator";
import DepartmentLabel from "../DepartmentLabel/DepartmentLabel";

const Departments = ({ authentication }) => {
    const [departments, setDepartments] = useState([]);

    return (
        <div className={styles.departments}>
            <div className={styles.departmentsHeader}>
                <p className={styles.departmentsHeaderText}>
                    Отделы
                </p>
                <IconButton src={'/assets/icons/add.png'}/>
            </div>
            <div className={styles.departmentsBody}>
                <InfiniteScrollPaginator params={{}} data={departments}
                    updateData={setDepartments} countByPage={10} endpoint={"/department/label/get"}
                    maxCountByPage={20} arrName={'trafficPoliceLabels'}>
                    {departments.map((department) => {
                        return <DepartmentLabel department={department}/>
                    })}
                </InfiniteScrollPaginator>
            </div>
        </div>
    );
};

export default Departments;