   /**
    * 亚洲时间
    * @param {*} date 
    * @returns 
    */
export const formatDateTimeCST = (date = new Date()) => {
    const parts = new Intl.DateTimeFormat('en-CA', {
      timeZone: 'Asia/Shanghai',
      year: 'numeric',
      month: '2-digit',
      day: '2-digit',
      hour: '2-digit',
      minute: '2-digit',
      second: '2-digit',
      hour12: false
    }).formatToParts(date)
    const map = Object.fromEntries(parts.map(p => [p.type, p.value]))
    return `${map.year}-${map.month}-${map.day}`
  }
