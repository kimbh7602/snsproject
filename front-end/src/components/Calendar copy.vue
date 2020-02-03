<template>
    <calendar
        :calendars="calendarList"
        :schedules="scheduleList"
        :view="view"
        :taskView="taskView"
        :scheduleView="scheduleView"
        :theme="theme"
        :week="week"
        :month="month"
        :timezones="timezones"
        :disableDblClick="disableDblClick"
        :isReadOnly="isReadOnly"
        :template="template"
        :useCreationPopup="useCreationPopup"
        :useDetailPopup="useDetailPopup"
        @beforeCreateSchedule="onBeforeCreateSchedule"
    />
</template>
<script>
import 'tui-calendar/dist/tui-calendar.css'
import { Calendar } from '@toast-ui/vue-calendar';
import $ from "jquery"
export default {
    name: 'myCalendar',
    components: {
        'calendar': Calendar
    },
    data() {
        return {
            calendarList: [
                {
                    id: '0',
                    name: 'home'
                },
                {
                    id: '1',
                    name: 'office'
                }
            ],
            scheduleList: [
                {
                    id: '1',
                    calendarId: '1',
                    title: 'my schedule',
                    category: 'time',
                    dueDateClass: '',
                    start: '2018-10-18T22:30:00+09:00',
                    end: '2018-10-19T02:30:00+09:00'
                },
                {
                    id: '2',
                    calendarId: '1',
                    title: 'second schedule',
                    category: 'time',
                    dueDateClass: '',
                    start: '2018-10-18T17:30:00+09:00',
                    end: '2018-10-19T17:31:00+09:00'
                }
            ],
            view: 'month',
            taskView: false,
            scheduleView: ['allday','time'],
            theme: {
                'month.dayname.height': '30px',
                'month.dayname.borderLeft': '1px solid #ff0000',
                'month.dayname.textAlign': 'center',
                'week.today.color': '#333',
                'week.daygridLeft.width': '100px',
                'week.timegridLeft.width': '100px'
            },
            week: {
                narrowWeekend: true,
                showTimezoneCollapseButton: true,
                timezonesCollapsed: false
            },
            month: {
                visibleWeeksCount: 4,
                startDayOfWeek: 1,
                visibleScheduleCount: 3
            },
            timezones: [{
                timezoneOffset: 540,
                displayLabel: 'GMT+09:00',
                tooltip: 'Seoul'
            }, {
                timezoneOffset: -420,
                displayLabel: 'GMT-08:00',
                tooltip: 'Los Angeles'
            }],
            disableDblClick: true,
            isReadOnly: false,
            template: {
                milestone: function(schedule) {
                    return `<span style="color:red;">${schedule.title}</span>`;
                },
                milestoneTitle: function() {
                    return 'MILESTONE';
                },
            },
            useCreationPopup: true,
            useDetailPopup: true,
        }
    },
    methods: {
        onBeforeCreateSchedule(scheduleData) {
            const schedule = {
                calendarId: scheduleData.calendarId,
                id: String(Math.random() * 100000000000000000),
                title: scheduleData.title,
                isAllDay: scheduleData.isAllDay,
                start: scheduleData.start,
                end: scheduleData.end,
                category: scheduleData.isAllDay ? 'allday' : 'time',
                location: scheduleData.location             // 장소 정보도 입력할 수 있네요!
            };

            // calendar.createSchedules([schedule]);
            this.$store.commit('setModalText', schedule.id+' 일정 생성 완료');
            document.getElementById('modalBtn').click();
        }
    },
    mounted() {
        $('html').scrollTop(0);        
    }
}
</script> 