import React from 'react';
import style from './style.module.css'
import SidebarItem from "../SidebarItem/SidebarItem";

const Sidebar = ({ authentication }) => {
    function requireRole(role) {
        if (authentication != null)
            return authentication.roles.includes(role, 0);
        return false;
    }

    return (
        <div className={style.sidebar}>
            <SidebarItem text={'Отделы'} link={'/'}/>
            {requireRole('employee') || requireRole('admin') ? <SidebarItem text={'Сотрудники'} link={'/employee'}/> : null}
            {requireRole('employee') || requireRole('admin') ? <SidebarItem text={'Технические инспекции'} link={'/inspection'}/> : null}
            {requireRole('employee') || requireRole('admin') ? <SidebarItem text={'Владельцы машин'} link={'/owner'}/> : null}
            {requireRole('employee') || requireRole('admin') ? <SidebarItem text={'Машины'} link={'/car'}/> : null}
            {requireRole('admin') ? <SidebarItem text={'Справочные таблицы'} link={'/table'}/> : null}
        </div>
    );
};

export default Sidebar;