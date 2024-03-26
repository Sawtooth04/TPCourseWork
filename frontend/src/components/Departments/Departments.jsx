import React, {useState} from 'react';
import styles from './style.module.css';
import IconButton from "../UI/IconButton/IconButton";
import InfiniteScrollPaginator from "../UI/InfiniteScrollPaginator/InfiniteScrollPaginator";
import DepartmentLabel from "../DepartmentLabel/DepartmentLabel";
import AddDepartment from "../AddDepartment/AddDepartment";

const Departments = ({ authentication }) => {
    const [departments, setDepartments] = useState([]);
    const [isAddDepartmentFormHidden, setIsAddDepartmentFormHidden] = useState(true);

    function switchAddDepartmentFormState() {
        setIsAddDepartmentFormHidden(!isAddDepartmentFormHidden);
    }

    return (
        <div className={styles.departments}>
            <AddDepartment isHidden={isAddDepartmentFormHidden} authentication={authentication}/>
            <div className={styles.departmentsHeader}>
                <p className={styles.departmentsHeaderText}>
                    Отделы
                </p>
                <IconButton src={'/assets/icons/add.png'} onClick={switchAddDepartmentFormState}/>
            </div>
            <div className={styles.departmentsBody}>
                <InfiniteScrollPaginator params={{}} data={departments}
                    updateData={setDepartments} countByPage={10} endpoint={"/department/label/get"}
                    maxCountByPage={20} arrName={'trafficPoliceLabels'}>
                    {departments.map((department) => {
                        return <DepartmentLabel department={department} key={department.trafficPoliceID}/>
                    })}
                </InfiniteScrollPaginator>
            </div>
        </div>
    );
};

export default Departments;